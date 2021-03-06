package client

import akka.actor.{Actor, ActorRef}
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import shared.ClientToGreetingMessages.ConnectionToGreetingQuery
import shared.Channels.GREETING_SERVER_RECEIVES_TOPIC


/** è un attore che ascolta i messaggi inviati su Channels.GREETING_SERVER_RECEIVES_TOPIC
 *    e li inoltra all'attore identificato da ActorRef dummyGreetingServer
 *
 *  - si sarebbe potuto usare un TestProbe se non fosse stato necessario usare mediator
 * @param dummyGreetingServer ActorRef dell'attore a cui mandare i messaggi ricevuti
 */
class GreetingServerTopicListener(dummyGreetingServer: ActorRef) extends Actor{
  val mediator: ActorRef = DistributedPubSub(context.system).mediator
  mediator ! Subscribe(GREETING_SERVER_RECEIVES_TOPIC, self)
  override def receive: Receive = {
    case connectionMessage : ConnectionToGreetingQuery => dummyGreetingServer ! connectionMessage
  }
}